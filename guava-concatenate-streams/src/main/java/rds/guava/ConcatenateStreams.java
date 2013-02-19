package rds.guava;

    import com.google.common.io.*;
    import java.io.*;

    public class ConcatenateStreams {
        public static void main(String[] args) throws Exception {
            InputStream malformedXmlContent = externalXmlStream();
            InputSupplier<InputStream> joined = ByteStreams.join(
                    inputSupplier("<root>"),
                    inputSupplier(malformedXmlContent),
                    inputSupplier("</root>"));
            ByteStreams.copy(joined, System.out);
        }

        private static InputStream externalXmlStream() {
            return new ByteArrayInputStream("<foo>5</foo><bar>10</bar>".getBytes());
        }

        private static InputSupplier<InputStream> inputSupplier(final String text) {
            return inputSupplier(new ByteArrayInputStream(text.getBytes()));
        }

        private static InputSupplier<InputStream> inputSupplier(final InputStream inputStream) {
            return new InputSupplier<InputStream>() {
                @Override
                public InputStream getInput() throws IOException {
                    return inputStream;
                }
            };
        }
    }
