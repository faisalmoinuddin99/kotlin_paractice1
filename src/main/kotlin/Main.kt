import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(
    val deviceName: String, private val deviceCategory: String,
    private val deviceType: String
) {
    fun printDeviceInfo() {
        println(
            "Device name: ${this.deviceName}, category: ${this.deviceCategory}, " +
                    "type: ${this.deviceType}"
        )
    }
}

class SmartTvDevice(name: String, tvCategory: String, tvType: String) : SmartDevice(
    deviceName = name, deviceCategory = tvCategory, deviceType = tvType
) {
    private var speakerVolume by RangeRegulator(1, 0, 100)
    private var channelNumber by RangeRegulator(2,1,200)

    fun increaseVolume(){
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun decreaseVolume(){
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume")
    }

    fun nextChannel(){
        channelNumber++
        println("Next Channel number $channelNumber")
    }

    fun previousChannel(){
        channelNumber--
        println("Previous Channel $channelNumber")
    }

    fun details(){
        println("Tv details - " +
                "Model: $deviceName " +
                "Volume: $speakerVolume " +
                "Channel Number: $channelNumber "

        )
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    private var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {

        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {

        if (value in minValue..maxValue) {
            fieldData = value
        }
    }

}

fun main() {
    val device1 = SmartTvDevice("Android Tv","Android","Digital")


    device1.details()
    device1.increaseVolume()
    device1.increaseVolume()
    device1.increaseVolume()
    device1.nextChannel()
    device1.decreaseVolume()
    device1.nextChannel()
    device1.nextChannel()
    device1.nextChannel()
    device1.previousChannel()
    device1.details()


}

/*
Tv details - Model: Android Tv Volume: 1 Channel Number: 2
Speaker volume increased to 2.
Speaker volume increased to 3.
Speaker volume increased to 4.
Next Channel number 3
Speaker volume decreased to 3
Next Channel number 4
Next Channel number 5
Next Channel number 6
Previous Channel 5
Tv details - Model: Android Tv Volume: 3 Channel Number: 5
 */