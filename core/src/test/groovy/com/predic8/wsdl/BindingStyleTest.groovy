/* Copyright 2012 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.predic8.wsdl

import javax.xml.stream.*

class BindingStyleTest extends AbstractWSDLTest {
  
  Binding binding
  def static wsdl = 
  '''<?xml version="1.0" encoding="UTF-8"?>
<wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                  xmlns="http://schemas.xmlsoap.org/wsdl/"
                  xmlns:wsaw="http://www.w3.org/2006/05/addressing/wsdl"
                  xmlns:tns="http://thomas-bayer.com/blz/"
                  xmlns:http="http://schemas.xmlsoap.org/wsdl/http/"
                  xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                  xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                  xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                  xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                  targetNamespace="http://thomas-bayer.com/blz/">
  <wsdl:documentation>BLZService</wsdl:documentation>
  <wsdl:types>
    <xsd:schema attributeFormDefault="unqualified" elementFormDefault="qualified" targetNamespace="http://thomas-bayer.com/blz/">
      <xsd:element name="getBank" type="tns:getBankType"/>
      <xsd:element name="getBankResponse" type="tns:getBankResponseType"/>
      <xsd:complexType name="getBankType">
        <xsd:sequence>
          <xsd:element name="blz" type="xsd:string"/>
        </xsd:sequence>
      </xsd:complexType>
      <xsd:complexType name="getBankResponseType">
        <xsd:sequence>
          <xsd:element name="details" type="tns:detailsType"/>
        </xsd:sequence>
      </xsd:complexType>
      <xsd:complexType name="detailsType">
        <xsd:sequence>
          <xsd:element minOccurs="0" name="bezeichnung" type="xsd:string"/>
          <xsd:element minOccurs="0" name="bic" type="xsd:string"/>
          <xsd:element minOccurs="0" name="ort" type="xsd:string"/>
          <xsd:element minOccurs="0" name="plz" type="xsd:string"/>
        </xsd:sequence>
      </xsd:complexType>
    </xsd:schema>
  </wsdl:types>

  <wsdl:message name="getBank">
    <wsdl:part name="parameters" element="tns:getBank"/>
  	<wsdl:part name="parameters2" type="xsd:string"/>
  </wsdl:message>

  <wsdl:message name="getBankResponse">
    <wsdl:part name="parameters3" type="xsd:string"/>
  </wsdl:message>

  <wsdl:portType name="BLZServicePortType">
    <wsdl:operation name="getBank">
      <wsdl:input message="tns:getBank"/>
      <wsdl:output message="tns:getBankResponse" wsaw:Action="http://thomas-bayer.com/blz/BLZService/getBankResponse"/>
    </wsdl:operation>
  </wsdl:portType>

  <wsdl:binding name="DocumentLiteralBinding" type="tns:BLZServicePortType">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
    <wsdl:operation name="getBank">
      <soap:operation soapAction="" style="document"/>
      <wsdl:input>
        <soap:body use="literal"/>
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal"/>
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>

  <wsdl:binding name="RPCLiteralBinding" type="tns:BLZServicePortType">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
    <wsdl:operation name="getBank">
      <soap:operation soapAction="" style="rpc"/>
      <wsdl:input>
        <soap:body use="literal"/>
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal"/>
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>

  <wsdl:binding name="RPCEncodedBinding" type="tns:BLZServicePortType">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
    <wsdl:operation name="getBank">
      <soap:operation soapAction="" style="rpc"/>
      <wsdl:input>
        <soap:body use="encoded"/>
      </wsdl:input>
      <wsdl:output>
        <soap:body use="encoded"/>
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  
  <wsdl:binding name="BLZServiceHttpBinding" type="tns:BLZServicePortType">
	  <http:binding verb="POST"/>
	  <wsdl:operation name="getBank">
		  <http:operation location="BLZService/getBank"/>
		  <wsdl:input>
		  	<mime:content type="text/xml" part="getBank"/>
		  </wsdl:input>
		  <wsdl:output>
		  	<mime:content type="text/xml" part="getBank"/>
	  	</wsdl:output>
	  </wsdl:operation>
  </wsdl:binding>

  <wsdl:binding name="NoStyleInSOAPBinding" type="tns:BLZServicePortType">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
    <wsdl:operation name="getBank">
      <soap:operation soapAction="" style="rpc"/>
      <wsdl:input>
        <soap:body use="literal"/>
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal"/>
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>

  <wsdl:service name="BLZService">
    <wsdl:port name="BLZServiceSOAP11port_http" binding="tns:BLZServiceSOAP11Binding">
      <soap:address location="http://www.thomas-bayer.com:80/axis2/services/BLZService"/>
    </wsdl:port>
    <wsdl:port name="BLZServiceSOAP12port_http" binding="tns:BLZServiceSOAP12Binding">
      <soap12:address location="http://www.thomas-bayer.com:80/axis2/services/BLZService"/>
    </wsdl:port>
    <wsdl:port name="BLZServiceHttpport" binding="tns:BLZServiceHttpBinding">
      <http:address location="http://www.thomas-bayer.com:80/axis2/services/BLZService"/>
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>
'''
	
  void testBinding() {
  	assert definitions.bindings.styleErrors
		assert definitions.bindings[3].styleErrors == []
		assert definitions.getBinding('DocumentLiteralBinding').binding.checkStyle()['result'] == 'Document/Literal'
		assert definitions.bindings.styleErrors.flatten().size() == 6
  }
}
