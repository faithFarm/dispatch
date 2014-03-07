<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<script type="text/javascript"> 

function moveOnMax(field,nextFieldID){
	  if(field.value.length >= field.maxLength){
	    document.getElementById(nextFieldID).focus();
	  }
	}

  function popitup(url) {
	newwindow=window.open(url,'name','resizable=no,scrollbars=0,height=140,width=600');
	if (window.focus) {newwindow.focus()}
	return false;
}


      function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode!=46 && (charCode > 31 && (charCode < 48 || charCode > 57)))
            return false;

         return true;
      }

window.gcoder = new google.maps.Geocoder();
$(document).ready(function() {
  

	$(".entry[name=zipcode]").change(function(e) {
	  gcoder.geocode({
	    'address': $(this).val()
	  }, function(res, status) {
	    window.res = res;
	    if(status == google.maps.GeocoderStatus.OK && res.length) {
	      for(var i=0;i<res[0].address_components.length;i++) {
	        var component = res[0].address_components[i];
	        
	        for(var x=0;x<component.types.length;x++) {
	          var tType = component.types[x];
	          if(tType == "administrative_area_level_1") {
	            $(".entry[name=state]").val(component.long_name);
	          }
	          if(tType == "locality") {
	            $(".entry[name=city]").val(component.long_name);
	          }
	        }
	      }
	    }
	  });
	});
	
});

 function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(26.153936, -80.153442),
          zoom: 15,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById('map_canvas'),
          mapOptions);

        var input = document.getElementById('searchTextField');
        var options = {
        		  types: ['(cities)'],
        		  componentRestrictions: {country: 'us'}
        		};
        var autocomplete = new google.maps.places.Autocomplete(input);

        autocomplete.bindTo('bounds', map);

        var infowindow = new google.maps.InfoWindow();
        var marker = new google.maps.Marker({
          map: map
        });

        google.maps.event.addListener(autocomplete, 'place_changed', function() {
          infowindow.close();
          var place = autocomplete.getPlace();
          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);  // Why 17? Because it looks good.
          }

          var image = new google.maps.MarkerImage(
              place.icon,
              new google.maps.Size(71, 71),
              new google.maps.Point(0, 0),
              new google.maps.Point(17, 34),
              new google.maps.Size(35, 35));
          marker.setIcon(image);
          marker.setPosition(place.geometry.location);

          var address = '';
          if (place.address_components) {
            address = [
              (place.address_components[0] && place.address_components[0].short_name || ''),
              (place.address_components[1] && place.address_components[1].short_name || ''),
              (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
          }

          infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
          infowindow.open(map, marker);
        });

        // Sets a listener on a radio button to change the filter type on Places
        // Autocomplete.
        function setupClickListener(id, types) {
          var radioButton = document.getElementById(id);
          google.maps.event.addDomListener(radioButton, 'click', function() {
            autocomplete.setTypes(types);
          });
        }

        setupClickListener('changetype-all', []);
        setupClickListener('changetype-establishment', ['establishment']);
        setupClickListener('changetype-geocode', ['geocode']);
      }
      google.maps.event.addDomListener(window, 'load', initialize);
  
  function printPage()
  {
    window.print();
  }

  function disableFields() {
    var count = document.forms[0].elements.length-1;
    var i;
    for (i=5;i<count;i++) {
      document.forms[0].elements[i].disabled=true;
    }
  }

  function enableFields() {
    var count = document.forms[0].elements.length-2;
    var i;
    for (i=5;i<count;i++) {
      document.forms[0].elements[i].enabled=true;
    }
  }

  
  function disableSave()
  {
  		document.getElementById("button1").disabled=true;
    	document.getElementById("button2").disabled=true;
    	document.getElementById("action").value="Save";
    	document.forms[0].submit();
  }
  function disableSavePrint()
  {
  		document.getElementById("button1").disabled=true;
    	document.getElementById("button2").disabled=true;
    	document.getElementById("action").value="Save & Print";
    	document.forms[0].submit();
  }
  function disableSaveChanges()
  {
  		document.getElementById("button1").disabled=true;
    	document.getElementById("button2").disabled=true;
    	document.getElementById("action").value="Save Changes";
    	document.forms[0].submit();
  }
  function disableSaveChangesPrint()
  {
  		document.getElementById("button1").disabled=true;
    	document.getElementById("button2").disabled=true;
    	document.getElementById("action").value="Save Changes & Print";
    	document.forms[0].submit();
  }


</script>

<style>
#td{}
.failureNotification
{
    font-size: .75em;
    horizontal-align: left;
    color: Red;
    font-family: "Helvetica Neue", "Lucida Grande", "Segoe UI", Arial, Helvetica, Verdana, sans-serif;
}
</style>

<jsp:include page="includes/header.jsp" flush="true"/>

<% 
	String required = "<img src='"+request.getContextPath()+"/images/required.png' title='required field'/>"; 
%>

<script language="javascript" type="text/javascript">
function ucase(obj) {
  obj.value=obj.value.toUpperCase();
}
function submit(action) {

	document.getElementById("action").value=action;
	document.getElementById("form1").submit();
}
</script>

<html:form method="POST" action="/Dispatch.do" styleId="form1"> 
			<tr>
				<td width="100%" align="left" valign="center" border="0" bgcolor="#FFFFFF">
						<% if (session.getAttribute("MESSAGE_"+session.getId())!=null) { %>
                        	<h5><img src="../images/success.png"/><%=session.getAttribute("MESSAGE_"+session.getId()) %></h5>
                        <% } %>
     
                        <ul>
                        <logic:notEmpty name="ticketForm" property="errors">
                            <i>Please correct the following errors:</i></br>
							<logic:iterate id="errors" name="ticketForm" property="errors">
	                        	<div class="failureNotification">
	                        	<li><b><bean:write name="errors" property="message"/></b></li>
	                        	</div>
	                        </logic:iterate>
                        </logic:notEmpty>
                        </ul>
  
				</td>
			<tr>
				<td width="100%" align="center" valign="center" border="0" bgcolor="#FFFFFF">
						
                        <h1>Donor Information
                        	<logic:notEmpty name="ticketForm" property="ticket.donationId">
								&nbsp;<i>(Confirmation #<bean:write name="ticketForm" property="ticket.donationId"/>, Call Date:<bean:write name="ticketForm" property="callDate"/>)</i>
                        	   <a href="javascript:submit('Print');"><img src="<%=request.getContextPath() %>/images/printer.png" height="20" width="20" title="Print Ticket" alt="Print Ticket"/></a>
                        	</logic:notEmpty>
                        </h1>
                                                
                		<table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="fieldHeading" >First Name<%=required%></td>
                                <td class="fieldHeading" >Last Name<%=required%></td> 
                                <td class="fieldHeading">Suffix</td>
                                <td></td>
                            </tr>
                                                       
                            <tr>
                            	<td class="searchField"><html:text size="20" maxlength="40" property="ticket.firstname"  styleClass="textbox" onkeyup="ucase(this)" /></td>
                                <td class="searchField"><html:text size="30" maxlength="40" property="ticket.lastname" styleClass="textbox" onkeyup="ucase(this)" /></td>
                                <td class="searchField"><html:text size="30" maxlength="40" property="ticket.suffix" styleClass="textbox" onkeyup="ucase(this)" /></td>
                                
                                <td class="searchField" width="50%" align="left">
                                	<html:submit property="action" value="SearchDonor" styleClass="imageButtonDonorSearch" title="Donor Search" />
                                </td>
                            </tr> 
                                                   
                        </table>  
                        
                        <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="fieldHeading" >Address Line 1<%=required%></td>
                            </tr>
                           
                            <tr>
                            	<td><html:text size="80" maxlength="100" property="ticket.addressLine1"  styleClass="textbox" onkeyup="ucase(this)" /></td>
                            </tr>   
                                                   
                        </table>
                        
                        <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="fieldHeading" >Address Line 2</td>
                            </tr>
                           
                            <tr>
                            	<td><html:text size="80" property="ticket.addressLine2"  maxlength="100" styleClass="textbox" onkeyup="ucase(this)" /></td>
                            </tr>                            
                        </table>
                        
                        <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="fieldHeading" >City<%=required%></td>
                                <td class="fieldHeading" >State<%=required%></td>
                                <td class="fieldHeading">Zipcode<%=required%></td>
                                <td width="50%"></td>
                            </tr>
                           
                            <tr>
                            	<td><html:text size="25" maxlength="25" property="ticket.city" styleClass="textbox" onkeyup="ucase(this)" /></td>
                                <td>
					                <html:select property="ticket.state" styleClass="ddl" >
										<html:option value="Florida">Florida</html:option>
									</html:select>
                                </td>
                                <td><html:text size="10" property="ticket.zipcode" maxlength="10" styleClass="textbox" /></td>
                                <td width="50%"></td>
                            </tr>     
                                               
                        </table>  
                        
                        <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="fieldHeading" >Contact Phone<%=required%></td>
                            	<td width="30"></td>
                            	<td class="fieldHeading" >Alternative Phone</td>
                            	<td width="30"></td>
                                <td class="fieldHeading" >Email Address</td>
                                <td width="20%"></td>
                            </tr>
                           
                            <tr>
                            	<td>
                                	<html:text size="20" maxlength="12" property="ticket.contactPhone" styleClass="textbox"  onkeypress="return maskPhone(event,this)" />
                                </td>
                                <td></td>
                                <td>
                                	<html:text size="20" maxlength="12" property="ticket.phoneOther" styleClass="textbox" onkeypress="return maskPhone(event,this)" /></td>
                                <td></td>
                                <td><html:text size="50" property="ticket.emailAddress" maxlength="50" styleClass="textbox" onkeyup="ucase(this)"/></td>
                                <td></td>
                            </tr>                            
                                                   
                        </table>  
                        
                        <table>
                        <tr>
            				<td height="20" bgcolor="#FFFFFF"></td>
            			</tr>
                        </table>
                        <hr class='dotted' />
                        <h1>Location Information</h1>
                        <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="fieldHeading" >Major Intersection<%=required%></td>
                                <td class="fieldHeading" >Subdivision<%=required%></td>
                                <td width="55%"></td>
                             </tr>
                             <tr>
                            	<td><html:text size="30" maxlength="30" property="ticket.majorIntersection" styleClass="textbox" onkeyup="ucase(this)" /></td>
                                <td><html:text size="30" maxlength="30" property="ticket.subdivision" styleClass="textbox" onkeyup="ucase(this)" /></td>
                                <td></td>
                             </tr>
                            
                         </table>
                         <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="fieldHeading" >Street Suffix<%=required%></td>
                                <td class="fieldHeading" >Structure Type<%=required%></td>
                                <td width="50" class="fieldHeading" >Unit</td>
                                <td class="fieldHeading" >Building</td>
                                <td class="fieldHeading" >Floor</td>
                                 <td width="40%"></td>
                             </tr>  
                              
                            <tr>
                            	<td>
 					                <html:select property="ticket.streetSuffix" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllStreetSuffix" value="value" label="label" />
									</html:select>
  
                                </td>
                                <td>
                                  	<html:select property="ticket.structureType" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllStructure" value="value" label="label" />
									</html:select>
  
                                  </select>
                                </td>
                                <td><html:text size="5" maxlength="5" name="ticketForm" property="ticket.unit"  styleClass="textbox"/></td>
                                <td><html:text size="5" maxlength="5" property="ticket.building"  styleClass="textbox"/></td>
                                <td>
                                	<html:select property="ticket.floor" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllFloor" value="value" label="label" />
									</html:select>
  
                                </td>
                                <td></td>
                            </tr>   
                                                  
                        </table>  
                        
                         <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td width="5" class="fieldHeading" ><html:checkbox property="ticket.elevatorFlag" value="YES" /></td>
                                <td class="fieldHeading" >Check if elevator access is available</td>
                            </tr> 
                            <tr>
                            	<td width="5" class="fieldHeading" ><html:checkbox property="ticket.gatedFlag" value="YES" /></td>
                                <td class="fieldHeading" >Check if community is gated</td>
                            </tr> 
                            <tr>
                            	<td colspan="2" class="fieldHeading" >Gate Instructions</td>
                            </tr>
                            <tr>
                                <td colspan="2" class="fieldHeading" >
 					                <html:select property="ticket.gateInstructions" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllGate" value="value" label="label" />
									</html:select>
                                </td>
                            </tr> 
                          
                            
                         </table>
                         
                          <table width="95%" cellpadding="0" cellspacing="0" border="0">
                         	<tr>
                            	<td class="fieldHeading" >Gate Code</td>
                            </tr>
                            <tr>
                            	<td class="fieldHeading" >
                            	   <html:text property="ticket.gateCode" size="20" maxlength="20" styleClass="textbox"/>
                                </td>
                            </tr>
                         
                        <table>
                        <tr>
            				<td height="20" bgcolor="#FFFFFF"></td>
            			</tr>
                        </table>
                        
                        <hr class='dotted' />
                         <h1>Donation Details</h1>
                         
                            <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td width="120" class="fieldHeading" >Status<%=required%></td>
                            </tr>
                            <tr>
                            	<td>
                                    <logic:equal name="ticketForm" property="ticket.status" value="PENDING">
	   					                <html:select property="ticket.status" styleClass="ddl" onchange="javascript:confirm('Confirm Cancellation?');">
											<html:optionsCollection name="dllStatus" value="value" label="label"  />
										</html:select>
									</logic:equal>
                                    <logic:notEqual name="ticketForm"  property="ticket.status" value="PENDING">
	   					                <html:select property="ticket.status" styleClass="ddl" >
											<html:optionsCollection name="dllStatus" value="value" label="label"  />
										</html:select>
									</logic:notEqual>
  
                            	</td>
                            </tr>
                        	
                        	<tr>
                            	<td width="120" class="fieldHeading" >Dispatch Date<%=required%></td>
                            </tr>
                            <tr>
                            	<td>
                                 	<html:text property="ticket.dispatchDate" size="12" styleClass="tcal" />
                                </td>
                            </tr>
                            
                            <tr>
                            	<td width="120" class="fieldHeading" >Item(s) Location</td>
                            </tr>
                            <tr> 
                            	<td>
    					            <html:select property="ticket.location" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllLocation" value="value" label="label" />
									</html:select>
 
                                  </td>
                            </tr>
                           
                            </table>
                            
                            <table width="95%" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                            	<td class="fieldHeading" >Special<%=required%></td>
                            </tr>
                            <tr>
                                <td class="fieldHeading" >
                                	<html:select property="ticket.specialFlag" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllYesNo" value="value" label="label" />
									</html:select></td>
                            </tr> 
                         </table>
                        <table width="95%" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                            	<td width="120" class="fieldHeading" >Call Requirements<%=required%></td>
                                <td class="fieldHeading" >
                                    <html:select property="ticket.callRequirements" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllCallReq" value="value" label="label" />
									</html:select>
                                 
                                </td>
                            </tr>   
                                                 
                         </table>
                        </br>
                           <table width="95%" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td class="itemName">A/C</td>
                                <td class="itemQuantity"><html:text property="ticket.ac" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Bedding</td>
                                <td class="itemQuantity">
                                	<html:text property="ticket.bedding"  size="2" maxlength="2" styleClass="textbox"/>
                                 	<html:select property="ticket.beddingQtyType" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllQtyType" value="value" label="label" />
									</html:select>

                                 </td>
                          		<td class="itemName">Books</td>
                                <td class="itemQuantity">
                                	<html:text property="ticket.books"  size="2" maxlength="2" styleClass="textbox"/>
                                	<html:select property="ticket.booksQtyType" styleClass="ddl" >
									<html:option value="">Select</html:option>
										<html:optionsCollection name="dllQtyType" value="value" label="label" />
									</html:select>

                                </td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                              <tr>
                            	<td class="itemName">Clothing</td>
                                <td class="itemQuantity">
                                	<html:text property="ticket.clothing"  size="2" maxlength="2" styleClass="textbox"/>
                                  	<html:select property="ticket.clothingQtyType" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllQtyType" value="value" label="label" />
									</html:select>
 
                                </td>
                                <td class="itemName">Computer</td>
                                <td class="itemQuantity"><html:text property="ticket.computer" size="2" maxlength="2" styleClass="textbox"/></td>
                          		<td class="itemName">Desk</td>
                                <td class="itemQuantity"><html:text property="ticket.desk"  size="2" maxlength="2" styleClass="textbox"/></td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                              <tr>
                            	<td class="itemName">Chest</td>
                                <td class="itemQuantity"><html:text property="ticket.chest" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Armoire</td>
                                <td class="itemQuantity"><html:text property="ticket.armoire" size="2" maxlength="2" styleClass="textbox"/></td>
                          		<td class="itemName">Dresser</td>
                                <td class="itemQuantity"><html:text property="ticket.dresser" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                              <tr>
                            	<td class="itemName">Mirror</td>
                                <td class="itemQuantity"><html:text property="ticket.mirror" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Nightstand</td>
                                <td class="itemQuantity"><html:text property="ticket.nightstand" size="2" maxlength="2" styleClass="textbox"/></td>
                          		<td class="itemName">Headboard</td>
                                <td class="itemQuantity"><html:text property="ticket.headboard" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                              <tr>
                            	<td class="itemName">Footboard</td>
                                <td class="itemQuantity"><html:text property="ticket.footboard" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Rails</td>
                                <td class="itemQuantity"><html:text property="ticket.rails" size="2" maxlength="2" styleClass="textbox"/></td>
                          		<td class="itemName">Lamp</td>
                                <td class="itemQuantity"><html:text property="ticket.lamp" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                              <tr>
                            	<td class="itemName">Lawn Furniture</td>
                                <td class="itemQuantity"><html:text property="ticket.lawnFurniture" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Mattress/Box Spring</td>
                                <td class="itemQuantity">
                                	<html:text size="2" maxlength="2" property="ticket.mattress" styleClass="textbox"/>
                                    <html:select property="ticket.mattressQtySize" styleClass="ddl" >
									<html:option value="">Select Size</html:option>
										<html:optionsCollection name="dllMattress" value="value" label="label" />
									</html:select>

                                </td>
                          		<td class="itemName">Misc Household Items</td>
                                <td class="itemQuantity">
                                	<html:text property="ticket.miscHouseholdItems" size="2" maxlength="2" styleClass="textbox"/>
                                	 <html:select property="ticket.miscHouseholdItemQtySize" styleClass="ddl" >
                                	 <html:option value="">Select</html:option>
										<html:optionsCollection name="dllQtyType" value="value" label="label" />
									</html:select>
                                </td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                              <tr>
                            	<td class="itemName">Refrigerator</td>
                                <td class="itemQuantity"><html:text property="ticket.refridgerator" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Stove</td>
                                <td class="itemQuantity"><html:text property="ticket.stove" size="2" maxlength="2" styleClass="textbox"/></td>
                          		<td class="itemName">Recliner</td>
                                <td class="itemQuantity"><html:text property="ticket.recliner" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                               <tr>
                            	<td class="itemName">Sofa</td>
                                <td class="itemQuantity"><html:text property="ticket.sofa" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Loveseat</td>
                                <td class="itemQuantity"><html:text property="ticket.loveseat" size="2" maxlength="2" styleClass="textbox"/></td>
                          		<td class="itemName">Wall Unit</td>
                                <td class="itemQuantity">
                                	<html:text property="ticket.wallUnit" size="2" maxlength="2" styleClass="textbox"/>
                                   	<html:select property="ticket.wallUnitQtySize" styleClass="ddl" >
										<html:option value="">Select Pieces</html:option>
										<html:optionsCollection name="dllPieces" value="value" label="label" />
									</html:select>
                                  
                                </td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                               <tr>
                            	<td class="itemName">Table</td>
                                <td class="itemQuantity"><html:text property="ticket.tables" size="2" maxlength="2" styleClass="textbox"/>
                                &nbsp;&nbsp;
                                    <html:select property="ticket.tableType" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllTableType" value="value" label="label" />
									</html:select>

                                
                                </td>
                                <td class="itemName">Chair</td>
                                <td class="itemQuantity"><html:text property="ticket.chair" size="2" maxlength="2" styleClass="textbox"/>
                                &nbsp;&nbsp;
                                   <html:select property="ticket.chairType" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllChairType" value="value" label="label" />
									</html:select>
                                </td>
                          		<td class="itemName">Television</td>
                                <td class="itemQuantity"><html:text property="ticket.television" size="2" maxlength="2" styleClass="textbox" onclick="javascript:alert('Please notify the donor that Faith Farm does not accept televisions that are more than 10 years old.  (The year can be located on the serial tag on the back of the televion.)');"/>
                                    <html:select property="ticket.televisionSize" styleClass="ddl" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllTvSize" value="value" label="label" />
									</html:select>
                                </td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                               <tr>
                            	<td class="itemName">Electronics</td>
                                <td class="itemQuantity"><html:text property="ticket.electronics" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Washer</td>
                                <td class="itemQuantity"><html:text property="ticket.washer" size="2" maxlength="2" styleClass="textbox"/></td>
                          		<td class="itemName">Dryer</td>
                                <td class="itemQuantity"><html:text property="ticket.dryer" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td></td>
                              </tr>
                              <tr><td colspan="6" height="1"></td></tr>
                               <tr>
                            	<td class="itemName">Exercise Equipment</td>
                                <td class="itemQuantity"><html:text property="ticket.exerciseEquipment" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Bookcase</td>
                                <td class="itemQuantity"><html:text property="ticket.bookcase" size="2" maxlength="2" styleClass="textbox"/></td>
                                <td class="itemName">Ottoman</td>
                                <td class="itemQuantity"><html:text property="ticket.ottoman" size="2" maxlength="2" styleClass="textbox"/></td>
                               </tr>
                               <tr><td colspan="6" height="1"></td></tr>
                               <tr>
                            	<td colspan="7" class="itemName" >Special Notes
                                	</br>
                                	<html:textarea styleClass="area" property="ticket.specialNotes" onkeyup="ucase(this)" />
                                </td>
                               </tr>
                            </table>
                            
                         
                         </br></br>
                         
                         <html:submit property="action" value="Save Ticket"  title="Save Ticket" />
                       
                          
               		
           	</td>
			</tr>
			
			<html:hidden property="action" value="" styleId="action" />
            <html:hidden property="key" value="" styleId="key" />
			
</html:form>			
<jsp:include page="includes/footer.jsp" flush="true" />
