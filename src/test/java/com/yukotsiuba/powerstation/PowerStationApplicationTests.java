package com.yukotsiuba.powerstation;

import com.yukotsiuba.powerstation.configuration.TestDBConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({TestDBConfig.class})
class PowerStationApplicationTests {

	@Test
	void contextLoads() {
	}

}
