package com.gof.microservice.config;

import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
//import com.hazelcast.config.Max;
import com.hazelcast.config.MaxSizePolicy;

@Configuration
public class CacheConfig {

	public Config chacheConfig() {
		return new Config().setInstanceName("hazelcast-cache")
				.addMapConfig(new MapConfig().setName("coupon-cache").setTimeToLiveSeconds(2000)
						.setEvictionConfig(new EvictionConfig().setSize(200)
								.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE).setEvictionPolicy(EvictionPolicy.LRU))

				);
	}

}
