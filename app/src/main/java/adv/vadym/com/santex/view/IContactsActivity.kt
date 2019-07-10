package adv.vadym.com.santex.view

interface IContactsActivity {
    fun dialogCreateMessage()
    fun onPhoneNumberClick(number: TelProvider)

    enum class TelProvider {
        LIFE, KVS, MTS, CITY
    }
}