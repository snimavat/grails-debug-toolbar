
target(createDebugPanel: "Creates a new Debug Panel.") {
    def prefix = argsMap["params"][0]
	
    def className = "${prefix}DebugPanel".toString()
    def artefactPath = 'grails-app/panels'
    def artefactFile = "${basedir}/${artefactPath}/${className}.groovy"
    if (new File(artefactFile).exists()) {
        ant.input(
                addProperty: "${args}.${className}.overwrite",
                message: "${className} already exists. Overwrite? [y/n]")
        
        if (ant.antProject.properties."${args}.${className}.overwrite" == "n") {
            return
        }
    }
    
    //Copy the template file to the 'grails-app/panels' directory.
    templateFile = "${debugToolbarPluginDir}/src/templates/artifacts/panels/DebugPanelTemplate.groovy"
    if (!new File(templateFile).exists()) {
        ant.echo("[DebugToolbar plugin] Error: src/templates/artifacts/panels/DebugPanelTemplate.groovy does not exist!")
        return
    }
    
    ant.copy(file: templateFile, tofile: artefactFile, overwrite: true)
    ant.replace(file: artefactFile) {
        ant.replacefilter(token: '@panel.name@', value: className)
    }
    
    event("CreatedFile", [artefactFile])
    event("CreatedArtefact", ['DebugPanel', className])
}