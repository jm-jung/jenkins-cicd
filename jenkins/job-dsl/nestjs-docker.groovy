job('NestJS Docker example') {
    scm {
        git('https://github.com/jm-jung/jenkins-cicd.git') {  node -> 
            node / gitConfigName('jm-jung')
            node / gitConfigEmail('jmsk7554@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Node18')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('orion319/docker-jenkins-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
