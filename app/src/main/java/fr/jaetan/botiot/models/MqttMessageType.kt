package fr.jaetan.botiot.models

enum class MqttMessageType(val id: Int) {
    Error(0),
    Action(1),
    Response(2),
    InfoAction(3),
    Final(9);

    companion object {
        fun fromInt(type: Int) = when (type) {
            1 -> Action
            2 -> Response
            3 -> InfoAction
            9 -> Final
            else -> Error
        }
    }
}