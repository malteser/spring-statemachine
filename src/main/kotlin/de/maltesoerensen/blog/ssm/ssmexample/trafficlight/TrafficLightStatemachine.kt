package de.maltesoerensen.blog.ssm.ssmexample.trafficlight

import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.StateMachineBuilder

fun buildTrafficLightStatemachine(): StateMachine<States, Events> {
    val builder = StateMachineBuilder.builder<States, Events>()

    builder.configureStates()
            .withStates()
            .states(States.values().toSet())
            .initial(States.Red)

    val logTransition = Action<States, Events> {
        context -> println("Transition from ${context.source.id} to ${context.target.id}")
    }

    builder.configureTransitions()

            .withExternal()
            .source(States.Red)
            .target(States.RedYellow)
            .event(Events.Cycle)
            .action(logTransition)

            .and()
            .withExternal()
            .source(States.RedYellow)
            .target(States.Green)
            .event(Events.Cycle)
            .action(logTransition)

            .and()
            .withExternal()
            .source(States.Green)
            .target(States.Yellow)
            .event(Events.Cycle)
            .action(logTransition)

            .and()
            .withExternal()
            .source(States.Yellow)
            .target(States.Red)
            .event(Events.Cycle)
            .action(logTransition)

    return builder.build()
}
