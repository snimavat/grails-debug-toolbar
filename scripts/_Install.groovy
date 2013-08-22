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

/**
 * Script invoked when the plugin is installed.
 * @author Kapil Sachdeva
 */
ant.mkdir(dir:"${basedir}/grails-app/panels")

// Copy the resources (Temporary - till I find a way to reference them)
ant.copy(todir:"${basedir}/web-app/debugtoolbar"){
    fileset(dir:"${debugToolbarPluginDir}/web-app/debugtoolbar")
}

// Copy the views (Temporary - till I find a way to reference them)
ant.copy(todir:"${basedir}/grails-app/views/debugtoolbar"){
    fileset(dir:"${debugToolbarPluginDir}/grails-app/views/debugtoolbar")
}
