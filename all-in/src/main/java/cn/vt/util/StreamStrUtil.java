package cn.vt.util;

import cn.hutool.core.util.StrUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class StreamStrUtil extends StrUtil {

    public static <T> StreamSupport<T> isNotEmpty(String str) {
        return new StreamSupport<T>(() -> StrUtil.isNotEmpty(str));
    }

    public static class StreamSupport<T> {

        private Supplier<Boolean> predicate;
        private Supplier<T> thenSupplier;
        private Supplier<T> orElseSupplier;

        public StreamSupport(Supplier<Boolean> predicate) {
            this.predicate = predicate;
        }

        public StreamSupport<T> then(Supplier<T> supplier) {
            thenSupplier = supplier;
            return this;
        }

        public StreamSupport<T> orElse(Supplier<T> supplier) {
            orElseSupplier = supplier;
            return this;
        }

        public Optional<T> get() {
            if (predicate.get()) {
                return Objects.isNull(thenSupplier) ? Optional.empty() : Optional.ofNullable(thenSupplier.get());
            } else {
                return Objects.isNull(orElseSupplier) ? Optional.empty() : Optional.ofNullable(orElseSupplier.get());
            }
        }
    }

    public static void main(String[] args) {
        String s = "123";
        StreamStrUtil.<Void>isNotEmpty("123")
                .then(() -> {
                    System.out.println("对了");
                    return null;
                })
                .orElse(() -> {
                    System.out.println("错了");
                    return null;
                })
                .get();
        System.out.println(
                StreamStrUtil.<String>isNotEmpty("123")
                        .then(() -> "非空")
                        .get().get()
        );
    }
}
