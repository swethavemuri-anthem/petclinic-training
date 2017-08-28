/**
 * 
 */
package com.anthem.nimbus.platform.client.extension.petclinic.view.home;

import com.anthem.oss.nimbus.core.domain.definition.Model;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Link;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.PageFooter;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.PageFooter.Property;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Model @Getter @Setter
public class VSHomeFooter {

	@Link(url="#") @PageFooter(Property.VERSION)
	private String appVersion;
	
	@Link(url="#") @PageFooter(Property.COPYRIGHT)
	private String appCopyright;

	@Link(url="#") @PageFooter(Property.PRIVACY)
	private String appPrivacy;
	
	@Link(url="#") @PageFooter(Property.TOU)
	private String appTou;
	
	@Link(url="#", imgSrc="verisign.png") @PageFooter(Property.SSLCERT)
	private String appSslCert;
	
}
