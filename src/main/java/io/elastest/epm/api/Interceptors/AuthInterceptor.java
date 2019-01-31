package io.elastest.epm.api.Interceptors;

import io.elastest.epm.exception.UnauthorizedException;
import io.elastest.epm.properties.KeystoneProperties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.openstack4j.api.exceptions.ConnectionException;

import java.net.ConnectException;

@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired private KeystoneProperties keystoneProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!keystoneProperties.isEnabled()) {
            log.info("Keystone authorisation is disabled, granting request");
            return true;
        }
        log.info("Authenticating with keystone");
        Identifier ident = Identifier.byName(keystoneProperties.getDomain());
        OSClientV3 os = null;
        try {
            os =
                    OSFactory.builderV3()
                            .endpoint(
                                    keystoneProperties.getEndpoint()
                                            + ":"
                                            + keystoneProperties.getPort()
                                            + "/"
                                            + keystoneProperties.getVersion())
                            .credentials(keystoneProperties.getUsername(), keystoneProperties.getPassword(), ident)
                            .authenticate();

        } catch (ConnectionException e) {
            log.info("Problem authentication with keystone, please, check if the credentials for access are correct");
            throw new Exception("Not able to authenticate with keystone to validate token");
        }

        log.info("Authenticated with keystone");
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer")) {
            log.info("Oath2 detected");
            authToken = authToken.substring(7, authToken.length());
        }
        try {
            if (authToken != null && os.identity().tokens().check(authToken).isSuccess()) {
                log.info("Token is valid. Grant request");
                return true;
            } else {
                log.info("Token is invalid. Request denied");
                throw new UnauthorizedException(
                        "Please use the valid keystone-token in order to authorize " + "for a request");
                //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please use the valid keystone-token in order to authorize for a request");
                //return false;
            }
        } catch (NullPointerException e) {
            throw new UnauthorizedException(
                    "There was a problem during the attempt to authorise with the provided token, "
                            + "please check the validity of token and the validity of keystone credentials");
        }
    }
}
