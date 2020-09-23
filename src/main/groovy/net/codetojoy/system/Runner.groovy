
package net.codetojoy.system

import net.codetojoy.custom.Config
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

        infosByCaseId.each { caseId, infos ->
            def region = ""
            def ageGroup = ""
            def recovered = ""

            infos.each { info ->
                if (info.region) { region = info.region }
                if (info.ageGroup) { ageGroup = info.ageGroup }
                if (info.recovered) { recovered = info.recovered }
            }

            if (utils.isEqualSafe(REGION_ATLANTIC, region)) {
                def prefix = ""
                if (! utils.isEqualSafe(RECOVERED, recovered)) {
                    prefix = "NONREC"
                }
                println "${prefix} ${caseId} ${region} ${ageGroup} ${recovered}"
            }
        }
    }

    def run(def infile) {
        println "TRACER run cp 1 before buildInfos"
        def infos = buildInfos(infile)

        def infosByCaseId = infos.groupBy { info -> info.caseId }
        println "TRACER run cp 2 after buildInfos"
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
