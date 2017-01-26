package uk.cooperca.entity.builder;

import uk.cooperca.entity.Script;

/**
 * Builder for the {@link Script} class.
 *
 * @author Charlie Cooper
 */
public class ScriptBuilder {

    private String path;

    public ScriptBuilder path(String path) {
        this.path = path;
        return this;
    }

    public Script build() {
        return new Script(path);
    }
}
