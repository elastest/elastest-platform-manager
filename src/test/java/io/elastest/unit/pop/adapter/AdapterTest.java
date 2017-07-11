package io.elastest.unit.pop.adapter;

import static org.mockito.Mockito.*;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.unit.core.CoreTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = AdapterTest.class)
public class AdapterTest {

  private final Logger log = LoggerFactory.getLogger(CoreTest.class);

  @Autowired private ConfigurableApplicationContext context;

  //    @Mock private ListImagesCmd listImagesCmd;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    log.info("Starting CoreTest");
  }

  @Test
  public void beanTest() {
    for (String s : context.getBeanDefinitionNames()) log.info(s);
  }

  @Bean
  @Qualifier("test_dockerAdapter")
  DockerAdapter dockerAdapter() {
    return new DockerAdapter();
  }

  @Bean
  @Qualifier("mocked_dockerClient")
  DockerClient dockerClient() {
    DockerClient dockerClient = mock(DockerClient.class);
    //build
    DefaultDockerClientConfig config = mock(DefaultDockerClientConfig.class);
    DockerClientConfig dockerClientConfig = mock(DockerClientConfig.class);
    DefaultDockerClientConfig.Builder builder = mock(DefaultDockerClientConfig.Builder.class);
    //        when(config.createDefaultConfigBuilder()).thenReturn(builder);
    //        when(builder.withDockerHost(anyString())).thenReturn(builder);
    when(builder.build()).thenReturn(config);
    //        DockerClientBuilder dockerClientBuilder = DockerClientBuilder.getInstance(config);
    //        when(dockerClientBuilder.getInstance(config)).thenReturn(dockerClientBuilder);
    //        when(dockerClientBuilder.build()).thenReturn(dockerClient);

    //Images
    ListImagesCmd listImagesCmd = mock(ListImagesCmd.class);
    List<Image> imageList = new ArrayList<>();
    when(dockerClient.listImagesCmd()).thenReturn(listImagesCmd);
    when(dockerClient.listImagesCmd().exec()).thenReturn(imageList);
    when(listImagesCmd.exec()).thenReturn(imageList);
    //        when(dockerClient.listImagesCmd()).thenReturn(listImagesCmd);
    //        MyImage image = mock(MyImage.class);
    //        when(image.getId()).thenReturn("mocked_image_id");
    //        when(image.getRepoTags()).thenReturn(new String[]{"mocked_image_tag"});
    //        imageList.add(image());
    when(dockerClient.listImagesCmd()).thenReturn(listImagesCmd);
    when(dockerClient.listImagesCmd().exec()).thenReturn(imageList);
    return dockerClient;
  }

  @Bean
  ListImagesCmd listImagesCmd() {
    ListImagesCmd listImagesCmd = mock(ListImagesCmd.class);
    return listImagesCmd;
  }
}
