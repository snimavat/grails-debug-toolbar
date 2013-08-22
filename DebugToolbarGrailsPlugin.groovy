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

import org.codehaus.groovy.grails.plugins.debugtoolbar.DebugPanelArtefactHandler;
import grails.util.GrailsUtil;


/**
 * @author Kapil Sachdeva
 * @since 0.1
 */
class DebugToolbarGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.1.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Kapil Sachdeva"
    def authorEmail = "ksachdeva17@gmail.com"
    def title = "A debug toolbar for Grails Web Applications"
    def description = '''\\
    	Grails Debug toolbar is a configurable set of panels that display various debug 
    	information about the current request/response.
    	It is inspired by the Django debug toolbar (http://robhudson.github.com/django-debug-toolbar/)
 '''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/DebugToolbar+Plugin"
	
	def watchedResources = "file:./grails-app/panels/**/*DebugPanel.groovy"
	def artefacts = [ DebugPanelArtefactHandler ]

    def doWithSpring = {
		
		def debugToolbarEnabled = application.config?.debugToolbar.enabled
		
		if(GrailsUtil.isDevelopmentEnv() || debugToolbarEnabled){
			
			// Configure debug panels defined in the project.
			def debugPanelBeans = []
			def debugPanelClasses = application.debugPanelClasses
			
			application.debugPanelClasses.each { debugPanelClass ->
				log.info "Registering DebugPanel: ${debugPanelClass.fullName}"
				configureDebugPanel.delegate = delegate
				
				debugPanelBeans << configureDebugPanel(debugPanelClass)
			}
			
		}
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def doWithWebDescriptor = { xml ->
    }

    def doWithDynamicMethods = { ctx ->
    }

    def onChange = { event ->
		
		def debugToolbarEnabled = application.config?.debugToolbar.enabled
		
		if(GrailsUtil.isDevelopmentEnv() || debugToolbarEnabled){
		
			if (application.isDebugPanelClass(event.source)) {
				log.info "DebugPanel modified!"
				
				def context = event.ctx
				if (!context) {
					log.debug("Application context not found - can't reload.")
					return
				}
				
				// Make sure the new debugPanel class is registered.
				def debugPanelClass = application.addArtefact(DebugPanelArtefactHandler.TYPE, event.source)
				
				// We clone the closure because we're going to change
				// the delegate.
				def beans = beans(configureDebugPanel.curry(debugPanelClass))
				beans.registerBeans(context)
			}
			
		}
    }

    def onConfigChange = { event ->
    }
	
	def configureDebugPanel = { grailsClass ->
		def debugPanelName = grailsClass.shortName
		
		// Create the debugPanel bean.
		"${debugPanelName}Instance"(grailsClass.clazz) { bean ->
			bean.autowire = "byName"
		}
		
		// Return the bean name for this debug panel.
		return "${debugPanelName}Instance";
	}
}
