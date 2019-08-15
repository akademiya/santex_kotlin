package adv.vadym.com.santex.view

interface IContactsActivity {
    fun dialogCreateMessage()
    fun onCallByNumber(number: TelProvider)
    fun showInvalidValue(errorField: InvalidValue)
    fun showErrorByLostFocus(errorField: InvalidValue)
    fun onResetNameError()
    fun onResetPhoneError()
    fun onResetMessageError()

    enum class InvalidValue {
        NO_NAME, NO_PHONE, INVALID_PHONE, NO_MESSAGE
    }

    enum class TelProvider {
        LIFE, KVS, MTS, CITY
    }
}