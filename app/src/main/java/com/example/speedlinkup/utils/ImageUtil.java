package com.example.speedlinkup.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.example.speedlinkup.R;
import com.example.speedlinkup.view.PieceImage;


/**
 * 图片资源工具类, 主要用于读取游戏图片资源值
 */
public class ImageUtil {
    /**
     * 保存所有连连看图片资源值(int类型)
     */
    private static List<Integer> imageValues = getImageValues();

    /**
     * 获取连连看所有图片的ID（约定所有图片ID以p_开头）
     */
    public static List<Integer> getImageValues() {
        try {
            // 得到R.drawable所有的属性, 即获取drawable目录下的所有图片
            Field[] drawableFields = R.drawable.class.getFields();
            List<Integer> resourceValues = new ArrayList<Integer>();
            for (Field field : drawableFields) {
                // 如果该Field的名称以fruit_开头
                if (field.getName().indexOf("cell") != -1) {
                    resourceValues.add(field.getInt(R.drawable.class));
                }
            }
            return resourceValues;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 随机从sourceValues的集合中获取size个图片ID, 返回结果为图片ID的集合
     *
     * @param sourceValues 从中获取的集合
     * @param size         需要获取的个数
     * @return size个图片ID的集合
     */
    public static List<Integer> getRandomValues(List<Integer> sourceValues,
                                                int size) {
        // 创建一个随机数生成器
        Random random = new Random();
        // 创建结果集合
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            try {
                // 随机获取一个数字，大于、小于sourceValues.size()的数值
                int index = random.nextInt(sourceValues.size());
                // 从图片ID集合中获取该图片对象
                Integer image = sourceValues.get(index);
                // 添加到结果集中
                result.add(image);
            } catch (IndexOutOfBoundsException e) {
                return result;
            }
        }
        return result;
    }

    /**
     * 从drawable目录中中获取size个图片资源ID(以p_为前缀的资源名称), 其中size为游戏数量
     *
     * @param size 需要获取的图片ID的数量
     * @return size个图片ID的集合
     */
    public static List<Integer> getPlayValues(int size) {
        if (size % 2 != 0) {
            // 如果该数除2有余数，将size加1
            size += 1;
        }
        // 再从所有的图片值中随机获取size的一半数量,即N/2张图片
        List<Integer> playImageValues = getRandomValues(imageValues, size / 2);
        // 将playImageValues集合的元素增加一倍（保证所有图片都有与之配对的图片），即N张图片
        playImageValues.addAll(playImageValues);
        // 将所有图片ID随机“洗牌”
        Collections.shuffle(playImageValues);
        return playImageValues;
    }


    //修改一下,把for循环取资源变成通过标记集合取资源

    /**
     * 将图片ID集合转换PieceImage对象集合，PieceImage封装了图片ID与图片本身
     *
     * @return size个PieceImage对象的集合
     */
    public static List<PieceImage> getPlayImages(Context context, int size) {
        // 获取图片ID组成的集合
        List<Integer> resourceValues = getPlayValues(size);
        List<PieceImage> result = new ArrayList<PieceImage>();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> set = sp.getStringSet("pathSet", null);
        if (null == set) {
            //用户未选择图片,使用默认图片
            // 遍历每个图片ID
            for (Integer value : resourceValues) {
                // 加载图片
                Bitmap bm = drawableToBitmap(context.getResources().getDrawable(value));
                // 封装图片ID与图片本身
                PieceImage pieceImage = new PieceImage(bm, value);
                result.add(pieceImage);
            }
        } else {
            //用户选择了图片,使用用户储存的图片
            List<String> list = new ArrayList<String>(set);
            List<String> resultList;
            //选择图片数量大于所需的图片数量时
            if (set.size() * 2 > size) {
                resultList = list.subList(0, size / 2);
            } else {
                //数量翻倍然后洗牌,最后加到list里面
                resultList = list;
            }
            resultList.addAll(resultList);
            Collections.shuffle(resultList);
            for (String path : resultList) {
                // 加载图片
                Bitmap bm = convertToBitmap(path, GameConf.PIECE_WIDTH, GameConf.PIECE_HEIGHT);
                // 封装图片ID与图片本身
                PieceImage pieceImage = new PieceImage(bm, path.hashCode());
                result.add(pieceImage);
            }
            //不够的从本地补
            if (list.size() < size) {
                List<Integer> resource = getPlayValues(size - list.size());
                for (Integer value : resource) {
                    // 加载图片
                    Bitmap bm = drawableToBitmap(context.getResources().getDrawable(value));
                    // 封装图片ID与图片本身
                    PieceImage pieceImage = new PieceImage(bm, value);
                    result.add(pieceImage);
                }
            }
            Collections.shuffle(result);
        }
        return result;
    }

    public static Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }


    /**
     * 将Drawable转换为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        }

        Bitmap bitmap = Bitmap.createBitmap(GameConf.PIECE_WIDTH, GameConf.PIECE_HEIGHT, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, GameConf.PIECE_WIDTH, GameConf.PIECE_HEIGHT);
        drawable.draw(canvas);
        return bitmap;
    }


    /**
     * 获取选中标识的图片
     *
     * @param context
     * @return 选中标识的图片
     */
    public static Bitmap getSelectImage(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.selected);
    }
}
