
package net.codetojoy.custom

import net.codetojoy.utils.Utils

// ﻿"REF_DATE","GEO","DGUID","Case identifier number","Case information","UOM","UOM_ID","SCALAR_FACTOR","SCALAR_ID","VECTOR","COORDINATE","VALUE","STATUS","SYMBOL","TERMINATED","DECIMALS"

class Info {
    def caseId
    def region
    def ageGroup
    def recovered

    static def utils = new Utils()

    static String getHeader() {
        utils.buildList(["Case Id", "Region", "Age Group", "Recovered"])
    }

    String toString() {
        utils.buildList([caseId, region, ageGroup, recovered])
    }
}
