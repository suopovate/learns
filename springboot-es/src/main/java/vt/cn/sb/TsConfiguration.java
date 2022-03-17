package vt.cn.sb;

import cn.hutool.core.lang.Console;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TsConfiguration implements BeanFactoryPostProcessor {

    public static void main(String[] args) {
        TestA[] declaredAnnotationsByType = TsConfiguration.class.getDeclaredAnnotationsByType(TestA.class);
        Console.log();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        configurableListableBeanFactory.registerScope("ThreadScope",new ThreadScope());
    }
}
