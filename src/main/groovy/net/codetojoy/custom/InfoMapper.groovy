
package net.codetojoy.custom

import net.codetojoy.utils.Utils

class InfoMapper {
    static final def INDEX_CASE_ID = 3
    static final def INDEX_CASE_INFO = 4
    static final def INDEX_VALUE = 11

    static final String REGION = "Region"
    static final String AGE_GROUP = "Age group"
    static final String RECOVERED = "Recovered"


// ﻿"REF_DATE","GEO","DGUID","Case identifier number","Case information",
// "UOM","UOM_ID","SCALAR_FACTOR","SCALAR_ID","VECTOR",
// "COORDINATE","VALUE","STATUS","SYMBOL","TERMINATED","DECIMALS"

    def utils = new Utils()

    def mapLine(def line) {
        def info = null

        try {
            def caseId = line.getAt(INDEX_CASE_ID)
            def caseInfo = line.getAt(INDEX_CASE_INFO)

            if (utils.isEqualSafe(caseInfo, REGION)) {
                def region = line.getAt(INDEX_VALUE)
                info = new Info(caseId: caseId, region: region)
            } else if (utils.isEqualSafe(caseInfo, AGE_GROUP)) {
                def ageGroup = line.getAt(INDEX_VALUE)
                info = new Info(caseId: caseId, ageGroup: ageGroup)
            } else if (utils.isEqualSafe(caseInfo, RECOVERED)) {
                def recovered = line.getAt(INDEX_VALUE)
                info = new Info(caseId: caseId, recovered: recovered)
            } else {
                // skip
            }
        } catch(Exception ex) {
            System.err.println("TRACER caught ex : ${ex.message}")
        }

        return info
    }
}
