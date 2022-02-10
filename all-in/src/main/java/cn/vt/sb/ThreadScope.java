package cn.vt.sb;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

public class ThreadScope implements Scope {

    private ThreadLocal<Map<String, Object>> threadSafeObjs = ThreadLocal.withInitial(HashMap::new);

    @Override
    public Object get(String beanName, ObjectFactory<?> objectFactory) {
        return threadSafeObjs.get().computeIfAbsent(beanName, bName -> objectFactory.getObject());
    }

    @Override
    public Object remove(String s) {
        return threadSafeObjs.get().remove(s);
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
