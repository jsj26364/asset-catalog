package info.jsjackson.catalog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import info.jsjackson.catalog.AssetCatalogApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetCatalogApplication.class)
@WebAppConfiguration
public class AssetCatalogApplicationTests {

	@Test
	public void contextLoads() {
	}

}
