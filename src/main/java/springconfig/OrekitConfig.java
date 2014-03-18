package springconfig;

import javax.annotation.PostConstruct;

import org.orekit.data.DataProvidersManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("OrekitConfig")
public class OrekitConfig 
{	
	@Bean(name="UTCTAI_PATH")
	public String getUTCTAI_PATH() 
	{
		return "/home/charlie/workspace/orekit-ee/SFDassS/data";
	}

	@PostConstruct
	public void initialize() 
	{
		System.setProperty(
			DataProvidersManager.OREKIT_DATA_PATH, 
			getUTCTAI_PATH());
	}
}
