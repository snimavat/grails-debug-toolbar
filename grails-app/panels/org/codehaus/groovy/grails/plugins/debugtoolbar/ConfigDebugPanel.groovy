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

/**
 * 
 * @author Kapil Sachdeva
 * @since 0.1
 */
class ConfigDebugPanel {
	
	def templateEngineService
	def grailsApplication
	
	def name = "Config"
	def has_content = true
	def nav_title = "Config"
	def title = "Config"
	def url = ''
	def dom_id = 'djDebugConfigPanel'
	def nav_subtitle = null
	
	def getContent(){
		return templateEngineService.renderView("/debugtoolbar/config",null)
	}
	
}
