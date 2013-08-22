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

import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * @author Kapil Sachdeva
 * @since 0.1
 */
class VersionDebugPanel implements InitializingBean {
	
	def templateEngineService
	def grailsApplication
	
	def name = 'Version'
	def nav_title = "Versions"
	def nav_subtitle = null
	def url = ''
	def title = "Version"
	def dom_id = 'djDebugVersionPanel'
	def has_content = true
	
	void afterPropertiesSet() {
		nav_subtitle = "Grails ${grailsApplication.metadata['app.grails.version']}";
	}
	
	def getContent(){
		return templateEngineService.renderView("/debugtoolbar/versions",null)
	}
}
