/**
 * 
 */
package com.anthem.nimbus.platform.client.extension.petclinic.view.home;

import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FooterProperty;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FooterProperty.Property;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Model @Getter @Setter
public class VSHomeFooter {
	/*
	 * Andrew Jo
	 * Issue: VERSION doesn't exist in ViewConfig.FooterProperty
	 */
	@Link(url="#") @FooterPropery(Property.VERSION)
	private String appVersion;
	/*
	 * Andrew Jo
	 * Issue: COPYRIGHT doesn't exist in ViewConfig.FooterProperty
	 */
	@Link(url="#") @FooterPropery(Property.COPYRIGHT)
	private String appCopyright;
	/*
	 * Andrew Jo
	 * PRIVACY doesn't exist in ViewConfig.FooterProperty
	 */
	@Link(url="#") @FooterPropery(Property.PRIVACY)
	private String appPrivacy;
	/*
	 * Andrew Jo
	 * Issue: TOU doesn't exist in ViewConfig.FooterProperty
	 */
	@Link(url="#") @FooterPropery(Property.TOU)
	private String appTou;
	
	@Link(url="#", imgSrc="verisign.png") @FooterPropery(Property.SSLCERT)
	private String appSslCert;
	
}
