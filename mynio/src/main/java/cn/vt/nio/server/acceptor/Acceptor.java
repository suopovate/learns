package cn.vt.nio.server.acceptor;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author vate
 */
public interface Acceptor{
    void accept(SelectionKey selectionKey) throws IOException;
}
