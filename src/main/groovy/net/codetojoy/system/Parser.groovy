
package net.codetojoy.system

import static com.xlson.groovycsv.CsvParser.parseCsv

class Parser {
    def infoMapper

    // use side-effect
    def parseFile = { def text, def infos  ->
        // println "TRACER parser cp 'A' size: " + infos.size()

        def data = parseCsv text

        data.each { def line ->
            def info = infoMapper.mapLine(line)
            if (info) {
                infos << info
            } else {
                // System.err.println "TRACER: skipping " + line
            }
        }
    }
}
