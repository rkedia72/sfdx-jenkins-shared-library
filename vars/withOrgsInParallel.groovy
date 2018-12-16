#!/usr/bin/env groovy
import com.claimvantage.jsl.Org

def call(Closure body = null) {
    
    def workspaceRoot = "${env.WORKSPACE}"
    env['WORKSPACE_ROOT'] = workspaceRoot
    
    withCredentials([file(credentialsId: env.JWT_CRED_ID_DH, variable: 'jwt_key_file')]) {
        def perOrgStages = [:]
        for (def scratchDefFile in findFiles(glob: 'config/project-scratch-def.*.json')) {
            Org org = new Org("${workspaceRoot}/${scratchDefFile.path}")
            perOrgStages["${org.name}"] = {
                if (body) {
                    echo "+++ Calling body ${org.name}"
                    body(org)
                    echo "+++ Returned from body ${org.name}"
                }
            }
        }
        parallel perOrgStages
    }
    
    /*
    withCredentials([file(credentialsId: env.JWT_CRED_ID_DH, variable: 'jwt_key_file')]) {
        def perOrgStages = [:]
        for (def scratchDefFile in findFiles(glob: 'config/project-scratch-def.*.json')) {
            Org org = new Org("${scratchDefFile.path}")
            perOrgStages["${org.name}"] = {
                if (body) {
                    body(org)
                }
            }
        }
        parallel perOrgStages
    }
    */
    
    /*
    def workspaceRoot = "${env.WORKSPACE}"
    env['WORKSPACE_ROOT'] = workspaceRoot
    
    def perOrgStages = [:]
    for (def scratchDefFile in findFiles(glob: 'config/project-scratch-def.*.json')) {
        node {
            Org org = new Org("${workspaceRoot}/${scratchDefFile.path}")
            perOrgStages["${org.name}"] = {
                ws(dir: "${workspaceRoot}/../withParallelOrgs-${org.name}") {
                    withCredentials([file(credentialsId: env.JWT_CRED_ID_DH, variable: 'jwt_key_file')]) {
                        if (body) {
                            echo "+++ Calling body ${org.name}"
                            body(org)
                            echo "+++ Returned from body ${org.name}"
                        }
                    }
                }
            }
        }
    }
    parallel perOrgStages
    */
    
    /*
    for (def file : findFiles(glob: glob)) {
        echo "Found ${file.path}"
        def org = new Org(projectScratchDefPath: file.path)
        if (body) {
            body([variable: org])
        }
    }
    
    return this
    */
}
