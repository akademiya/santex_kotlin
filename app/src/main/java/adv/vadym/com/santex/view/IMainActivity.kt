package adv.vadym.com.santex.view

interface IMainActivity {
    fun dialogCreateMessageOrder()
    fun showInvalidValue(errorField: InvalidValue)
    fun showErrorByLostFocus(errorField: InvalidValue)
    fun onReservedDate(date: String)
    fun onResetNameError()
    fun onResetPhoneError()
    fun onResetTimeError()

    enum class InvalidValue {
        NO_NAME, NO_PHONE, INVALID_PHONE, NO_TIME, INVALID_TIME
    }
}