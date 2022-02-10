package cn.vt.nio.server.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @author vate
 */
public class WorkReactor implements Reactor {

    private final Selector ioSelector;

    public WorkReactor(Selector ioSelector) {
        this.ioSelector = ioSelector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ioSelector.select(100);
                Set<SelectionKey> selected = ioSelector.selectedKeys();
                for (SelectionKey selectionKey : selected) {
                    //Reactor负责dispatch收到的事件
                    dispatch(selectionKey);
                }
                selected.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
