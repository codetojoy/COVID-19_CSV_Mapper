
package net.codetojoy.system

import net.codetojoy.custom.Config
import net.codetojoy.custom.Info
import net.codetojoy.utils.Utils

class Runner {

    def parser
    def outputHeader

    def Runner() {
        def config = new Config()
        parser = config.parser
        outputHeader = config.outputHeader
    }

    def buildInfos(def infile) {
        def infos = []
        def isHeader = true
        def header = ""

        new File(infile).eachLine { line ->
            if (isHeader) {
                isHeader = false
                header = line
            } else {
                def text = "${header}\n${line}\n"
                // side-effect
                parser.parseFile(text, infos)
            }
        }

        return infos
    }

    static final String REGION_ATLANTIC = "1"
    static final String RECOVERED = "1"

    def generateOutput(def infosByCaseId) {
        def utils = new Utils()

        println Info.getHeader()

        infosByCaseId.each { caseId, infos ->
            def unifiedInfo = new Info(caseId: caseId)

            // these all have the same caseId
            infos.each { info ->
                if (info.region) { unifiedInfo.region = info.region }
                if (info.ageGroup) { unifiedInfo.ageGroup = info.ageGroup }
                if (info.recovered) { unifiedInfo.recovered = info.recovered }
            }

            if (utils.isEqualSafe(REGION_ATLANTIC, unifiedInfo.region)) {
                println unifiedInfo.toString()
            }
        }
    }

    def run(def infile) {
        def infos = buildInfos(infile)
        def infosByCaseId = infos.groupBy { info -> info.caseId }
        generateOutput(infosByCaseId)
    }

    def static void main(String[] args) {
        if (args.size() < 1) {
            println "Usage: groovy Runner.groovy infile"
            System.exit(-1)
        }

        def infile = args[0]
        assert new File(infile).exists()

        new Runner().run(infile)
    }
}
