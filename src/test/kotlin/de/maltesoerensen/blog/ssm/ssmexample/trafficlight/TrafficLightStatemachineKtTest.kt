package de.maltesoerensen.blog.ssm.ssmexample.trafficlight

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TrafficLightStatemachineKtTest {

    private val stateMachine = buildTrafficLightStatemachine()

    @BeforeEach
    fun startStateMachine() = stateMachine.start()

    @Test
    fun initialStateIsRED() {
        assertThat(stateMachine.state.id).isEqualTo(States.Red)
    }

    @Test
    fun fullCycle() {
        stateMachine.sendEvent(Events.Cycle)
        assertThat(stateMachine.state.id).isEqualTo(States.RedYellow)
        stateMachine.sendEvent(Events.Cycle)
        assertThat(stateMachine.state.id).isEqualTo(States.Green)
        stateMachine.sendEvent(Events.Cycle)
        assertThat(stateMachine.state.id).isEqualTo(States.Yellow)
        stateMachine.sendEvent(Events.Cycle)
        assertThat(stateMachine.state.id).isEqualTo(States.Red)
    }
}