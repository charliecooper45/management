package uk.cooperca.entity.builder;

import uk.cooperca.entity.Script;

/**
 * Builder for the {@link Script} class.
 *
 * @author Charlie Cooper
 */
public class ScriptBuilder extends AbstractBuilder<Script> {

    private String command;

    public ScriptBuilder() {}

    public ScriptBuilder(Script script) {
        super(script);
        this.command = script.getCommand();
    }

    public ScriptBuilder command(String command) {
        this.command = command;
        return this;
    }

    @Override
    public Script build() {
        return new Script(id, command);
    }
}
