/**
 *
 */
package com.atlas.petclinic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.antheminc.oss.nimbus.domain.cmd.exec.ExecuteOutput.GenericExecute;
import com.antheminc.oss.nimbus.entity.client.user.ClientUser;
import com.antheminc.oss.nimbus.support.JustLogit;


/**
 * @author Swetha Vemuri
 * @author Dileep Roopreddy
 */
@Controller
public class LoginController {
	
	@Autowired
	private RestTemplate restTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	response.sendRedirect("/petclinic/#/h/petclinicdashboard/vpDashboard");
        
    }
    
    @RequestMapping(value="/test", method= RequestMethod.GET)
    public void test() {
    	UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString("http://localhost:8082/petclinic/client/org/app/p/clientuser/_search")
			    .queryParam("fn", "query")
			    .queryParam("where", "clientuser.loginId.in('asnowball')");
		
		
		ResponseEntity<GenericExecute<List<ClientUser>>> multiOutputResp = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, 
				null, new ParameterizedTypeReference<GenericExecute<List<ClientUser>>>() {});
		
		GenericExecute<List<ClientUser>> multiOutputRespBody = multiOutputResp.getBody();
		
    }
   
    private JustLogit _logger = new JustLogit(this.getClass());
}
