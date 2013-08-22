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
class HeaderDebugPanel {
	
	def templateEngineService
	
	def name = "Header"
	def has_content = true
	def nav_title = "HTTP Headers"
	def title = "HTTP Headers"
	def url = ''
	def dom_id = 'djDebugHeaderPanel'
	def nav_subtitle = null
	
	// List of headers we want to display
    def header_filter = [
        'Content-Type',
		'Content-Length',
        'Accept',
        'Accept-Charset',
        'Accept-Encoding',
        'Accept-Language',
        'Cache-Control',
        'Connection',
        'Host',
        'Keep-Alive',
        'Referer',
        'User-Agent',
        'Authorization']
	
	def getContent(){
		def request = RCH.currentRequestAttributes().currentRequest
		
		// prepare a map
		def headers = new HashMap()
		
		header_filter.each {
			def headerValue = request.getHeader(it)
			headers.put(it, headerValue)
		}
		
		def model = [
				'headers' : headers,
				]
		
		return templateEngineService.renderView("/debugtoolbar/headers",model)
	}	
}

