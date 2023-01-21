import javax.swing.AbstractButton
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
    private var channelNumber by RangeRegulator(2, 1, 200)

    fun increaseVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun decreaseVolume() {
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume")
    }

    fun nextChannel() {
        channelNumber++
        println("Next Channel number $channelNumber")
    }

    fun previousChannel() {
        println("Previous Channel $channelNumber")
        channelNumber--
    }

    fun details() {
        println(
            "Tv details - " +
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

class SmartHome(private val smartTvDevice: SmartTvDevice) {

    fun productDetails() {
        smartTvDevice.details()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseVolume()
    }

    fun decreaseTvVolume() {
        smartTvDevice.decreaseVolume()
    }

    fun changeTvChannel(upButton: Boolean?, downButton: Boolean?) {


        when {
            upButton == true -> smartTvDevice.nextChannel()
            downButton == true -> smartTvDevice.previousChannel()
            else -> "Invalid button clicked"
        }
    }
}

fun main() {
    val device1 : SmartHome = SmartHome(
        SmartTvDevice("Android Tv", "Entertainment", "Android")
    )

    device1.productDetails()
    device1.increaseTvVolume()
    device1.changeTvChannel(true,null)
    device1.productDetails()
    device1.changeTvChannel(null,true)
    device1.productDetails()

}

/*
Speaker volume increased to 2.
Next Channel number 3
Tv details - Model: Android Tv Volume: 2 Channel Number: 3
Previous Channel 3
Tv details - Model: Android Tv Volume: 2 Channel Number: 2
 */