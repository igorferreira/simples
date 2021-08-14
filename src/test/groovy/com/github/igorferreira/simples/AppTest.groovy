package com.github.igorferreira.simples

import spock.lang.Specification
import com.github.igorferreira.simples.App

class AppTest extends Specification {

    def "Dado uma data return true"(){
        given:
            String startDate = "2021-08-11T21:50:30Z"
            String endDate = "2022-08-12T21:50:30Z"
        when:
            boolean moreTheOneYear = App.isMoreOneYear(startDate,endDate)
            
        then:
            moreTheOneYear 
    }
}