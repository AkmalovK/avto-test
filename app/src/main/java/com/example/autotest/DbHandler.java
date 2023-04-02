package com.example.autotest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db";
    private static final int SCHEMA = 1;

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS \"Test\";");
        sqLiteDatabase.execSQL(String.format("CREATE TABLE \"Test\" (\n\t\"id\"\tINTEGER UNIQUE,\n\t\"image\"\tINTEGER,\n\t\"question\"\tTEXT,\n\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n)"));

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS \"Answers\";");
        sqLiteDatabase.execSQL("CREATE TABLE \"Answers\" (\n" +
                "\t\"Name\"\tTEXT,\n" +
                "\t\"IsTrue\"\tINTEGER,\n" +
                "\t\"image\"\tINTEGER,\n" +
                "\tFOREIGN KEY(\"image\") REFERENCES \"Test\"(\"id\")\n" +
                ")");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS \"Result\";");
        sqLiteDatabase.execSQL("CREATE TABLE \"Result\" (\n" +
                "\t\"image\"\tINTEGER,\n" +
                "\t\"question\"\tTEXT,\n" +
                "\t\"answer\"\tTEXT,\n" +
                "\t\"istrue\"\tINTEGER\n" +
                ")");

        List<Integer> images = Arrays.asList(
                R.drawable.image12,
                R.drawable.image11,
                R.drawable.image10,
                R.drawable.image9,
                R.drawable.image8,
                R.drawable.image7,
                R.drawable.image6,
                R.drawable.image5,
                R.drawable.image4
//                R.drawable.image10,
//                R.drawable.image11,
//                R.drawable.image12
        );
        List<String> questions = Arrays.asList(
                "Harakatlanish taqiqlangan:",
                "Qaysi transport vositasining haydovchisi chorrahadan birinchi bo`lib o`tadi?",
                "Transport vositalari chorrahadan quyidagi tartibda o`tadilar:",
                "Bunday taniqlik belgisi bilan belgilanadigan transport vositasi:",
                "Bu belgilarning ta`sir oralig`ida qaysi avtomobil uchun to`xtashga ruxsat beriladi?",
                "Qaysi yo`nalishlarda harakatlanishga ruxsat etilgan?",
                "O`ngga burilganda shu belgi bilan belgilangan bo`lakka o`tishga ruxsat etiladimi?",
                "Qaysi belgi aholi yashaydigan joylarda harakatlanish tartibini belgilaydigan talablarning bekor qilinishini ko`rsatadi?",
                "Yo`naltirgichlarning qaysilari bo`ylab harakatlanishga ruxsat etiladi?",
                "Chorrahadan birinchi bo`lib o`tadi:",
                "Qaysi belgi ro`para harakatlanishning ustunligini bildiradi?",
                "Shu joyda to`xtab turishga ruxsat etiladimi?"
        );

        for (int i = 0; i < images.size(); i++) {
            sqLiteDatabase.execSQL(String.format("INSERT INTO Test(id, image, question) \n" +
                            "VALUES(%d, %d, \"%s\");",
                    i+1, images.get(i), questions.get(i)));
        }

        List<List<String>> answers = Arrays.asList(
                Arrays.asList(
                        "Ko`k, yashil va oq avtomobillarga",
                        "Oq, ko`k va sariq avtomobillarga",
                        "Qizil va oq avtomobillarga"
                ),
                Arrays.asList(
                        "Avtomobil va avtobus haydovchisi",
                        "Tramvay haydovchisi"
                ),
                Arrays.asList(
                        "Yengil avtomobil, tramvay va avtobus",
                        "Tramvay, yengil avtomobil, avtobus",
                        "Tramvay va avtobus, yengil avtomobil"
                ),
                Arrays.asList(
                        "Uzunligi yuk bilan yoki yuksiz 20 metrdan ortiq bo`lgan transport vositasi",
                        "Og`ir vaznli va yirik o`lchamli yuklarni tashuvchi",
                        "Furgon yukxonasida odamlarni tashuvchi"
                ),
                Arrays.asList(
                        "Nogiron taniqlik belgisi bo`lgan sariq avtomobilga",
                        "Hech qaysisiga",
                        "Har ikkala avtomobilga",
                        "Qizil avtomobilga"
                ),
                Arrays.asList(
                        "A va Б",
                        "Faqat Б",
                        "A, Б va В",
                        "Faqat A",
                        "A, Б, В va Г"
                ),
                Arrays.asList(
                        "Agar bo`lak qolgan harakat qismidan yotiq sidirg`a chiziq bilan ajratilmagan bo`lsa, ruxsat etiladi",
                        "Taqiqlanadi",
                        "Ruxsat etiladi"
                ),
                Arrays.asList(
                       "5",
                       "1",
                       "2",
                       "3",
                       "4"
                ),
                Arrays.asList(
                        "Faqat A va Г yo`nalishlar bo`ylab",
                        "Faqat Г yo`nalish bo`ylab",
                        "Faqat Б yo`nalish bo`ylab",
                        "Faqat В yo`nalish bo`ylab",
                        "Faqat A yo`nalish bo`ylab"
                )
        );

        for(int i = 0; i < answers.size(); i++) {
            for (int j = 0; j < answers.get(i).size(); j++) {
                sqLiteDatabase.execSQL(String.format("INSERT INTO Answers(Name, isTrue, image) \n" +
                                "VALUES(\"%s\", %d, %d);",
                        answers.get(i).get(j), (j == 0 ? 1: 0), i+1
                ));
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Answers;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Test;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Result;");
        onCreate(sqLiteDatabase);
    }
}
