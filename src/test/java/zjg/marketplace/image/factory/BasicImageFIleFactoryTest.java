package zjg.marketplace.image.factory;

import zjg.marketplace.core.image.entity.BasicImageFile;
import zjg.marketplace.core.image.valueObj.path.ImagePath;

import java.nio.file.Files;
import java.nio.file.Path;

public class BasicImageFIleFactoryTest {
      private static String defaultPath = "test/image.jpeg";
      public static BasicImageFile factory() {
            var bytes = readTestImage();
            var path = new ImagePath(defaultPath);
            return new BasicImageFile(bytes, path);
      }

      private static byte[] readTestImage() {
            var resources = BasicImageFIleFactoryTest.class
                    .getClassLoader()
                    .getResourceAsStream(defaultPath);
            try {
                  return resources.readAllBytes();
            }
            catch (Exception e) {
                  throw new RuntimeException(e);
            }
      }
}
