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

import org.springframework.web.context.request.RequestContextHolder as RCH

/**
 * 
 * @author Kapil Sachdeva
 * @since 0.1
 */
class RequestVarsDebugPanel {
	
	def templateEngineService
	
	def name = "RequestVars"
	def has_content = true
	def nav_title = "Request Variables"
	def title = "Request Variables"
	def url = ''
	def dom_id = 'djDebugRequestVarsPanel'
	def nav_subtitle = null
	
	def getContent(){
		
		def request = RCH.currentRequestAttributes()		
		
		def model = [
		    'sess' : request.session,
		    'cookies' : request.currentRequest.cookies,
		    'get' : request.currentRequest.method == "GET" ? request.params : null,
		    'post' : request.currentRequest.method == "POST" ? request.params : null,
		]
		
		return templateEngineService.renderView("/debugtoolbar/request_vars",model)
	}	
}
