package app.naviallife.model

class WeekDiet {
    var food: String = ""
    var meel_time: String = ""
    var day: String = ""
    constructor() {}

    constructor(name: String, comment: String,day:String) {
        this.food = name
        this.meel_time = comment
        this.day=day
    }
}