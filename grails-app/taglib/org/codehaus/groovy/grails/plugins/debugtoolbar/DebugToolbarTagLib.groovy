/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.codehaus.groovy.grails.plugins.debugtoolbar;

import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

/**
 * 
 * @author Kapil Sachdeva
 * @since 0.1
 */
class DebugToolbarTagLib {
	
	static namespace = "debugToolbar"
	
	def renderMe = {
		
		def debugToolbarEnabled = grailsApplication.config?.debugToolbar.enabled
		
		if(GrailsUtil.isDevelopmentEnv() || debugToolbarEnabled){
		
			// get the panels
			def panels = grailsApplication.debugPanelClasses.collect { 
							grailsApplication.mainContext.getBean("${it.shortName}Instance") 
						 }
			
			out << """
			
			<script type="text/javascript" src="${resource(dir:'debugtoolbar',file:'jquery.js')}"></script>
	        <script type="text/javascript" src="${resource(dir:'debugtoolbar',file:'jquery.cookie.js')}"></script>
			<script type="text/javascript" src="${resource(dir:'debugtoolbar',file:'toolbar.js')}"></script>
			<link rel="stylesheet" href="${resource(dir:'debugtoolbar',file:'toolbar.css')}" />
			
			<div id="djDebug">
				<div style="display:none;" id="djDebugToolbar">
					<ul id="djDebugPanelList">
					
		    """
			
				if(panels != null){
					out << """
							<li><a id="djHideToolBarButton" href="#" title="Hide Toolbar">Hide &raquo;</a></li>
					"""
				}else{
		            out << '<li id="djDebugButton">DEBUG</li>'
		    		
				}
				
				panels.each { panel ->
					out << "<li>"
					
					if(panel.has_content){
						out << "<a href=${panel.url} title=${panel.title} class=${panel.dom_id}>"
					}else{
						out << '<div class="contentless">'
					}
					
					out << panel.nav_title
					out << "<br><small>"
					out << panel.nav_subtitle
					out << "</small>"
					
					if(panel.has_content){
						out << '</a>'
					}else{
						out <<  '</div>'
					}
					
					out << "</li>"
				}
				
			out << """
					</ul>
				</div>
			"""
	
			out << """
			
				<div style="display:none;" id="djDebugToolbarHandle">
					<a title="Show Toolbar" id="djShowToolBarButton" href="#">&laquo;</a>
				</div>
	
			"""
			
				panels.each { panel ->
					if(panel.has_content){
						out << """
						<div id="${panel.dom_id}" class="panelContent">
							<div class="djDebugPanelTitle">
								<a href="" class="djDebugClose">Close</a>
								<h3>${panel.title}</h3>
							</div>
							<div class="djDebugPanelContent">
							    <div class="scroll">
							        ${panel.content}
							    </div>
							</div>
						</div>
						"""
					}
				}
	
			out << """
				<div id="djDebugWindow" class="panelContent"></div>
			</div>
			
			"""
		}
	}
}
