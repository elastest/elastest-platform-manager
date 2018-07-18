/*
 *
 *  * (C) Copyright 2016 NUBOMEDIA (http://www.nubomedia.eu)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package io.elastest.epm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by maa on 22.01.16.
 */
@Service
@ConfigurationProperties(prefix = "docker")
public class DockerProperties {

    private LogStash logStash;

    private String compose_ip;

    private Registration registration;

    public LogStash getLogStash() {
        return logStash;
    }

    public void setLogStash(LogStash logStash) {
        this.logStash = logStash;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getCompose_ip() {
        return compose_ip;
    }

    public void setCompose_ip(String compose_ip) {
        this.compose_ip = compose_ip;
    }

    @Override
    public String toString() {
        return "DockerProperties{" + "logStash=" + logStash + ", registration=" + registration + '}';
    }

    public static class LogStash {
        private boolean enabled;

        private String address;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "LogStash{" + "enabled=" + enabled + ", address='" + address + '\'' + '}';
        }
    }

    public static class Registration {
        private String name;

        private boolean auto;

        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isAuto() {
            return auto;
        }

        public void setAuto(boolean auto) {
            this.auto = auto;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Registration{"
                    + "name='"
                    + name
                    + '\''
                    + ", auto="
                    + auto
                    + ", address='"
                    + address
                    + '\''
                    + '}';
        }
    }
}
