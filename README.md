# 📱 Kval-Exam App

## 🎯 Функционал приложения
- **Splash Screen** с облаками (1.5 сек)
- **Главное меню** с 4 кнопками:
  - Профиль (хранение данных + фото)
  - Медиа (аудио/видео плеер)
  - Генератор случайных чисел
- **Технологии**:  
  - `SharedPreferences` для сохранения профиля  
  - `MediaPlayer` + `VideoView` 

## 🛠 Технологии  
- Kotlin, Android SDK
- SharedPreferences (хранение данных)
- MediaPlayer, VideoView (аудио/видео)
- ConstraintLayout (верстка)
- Шрифты: `permanent_marker.ttf`, `troubleside.ttf`

## 🎨 Дизайн
- Цвета: фиолетовый фон (`#C7BAD7`), акценты (`#7711DD`)
- Кнопки: желтые/голубые/оранжевые с закруглениями
- Анимации: плавные переходы между экранами

## 📂 Структура проекта

### 1. Иерархия модуля app (Kotlin)
```
/res
/drawable - иконки и изображения (облака, кнопки)
/font - шрифты
/raw - медиафайлы
/values - colors, strings, dimens
```

### 2. Ресурсы (res)
```
res/  
├── drawable/  
│   ├── cloud_1.png, cloud_2.png, cloud_3.png  
│   ├── button_bg_blue.xml (селектор кнопок)  
│   └── circle_bg.xml (фон аватарки)  
├── font/  
│   ├── permanent_marker.ttf (английский текст)  
│   └── troubleside.ttf (кириллица)  
├── layout/  
│   ├── activity_splash.xml  
│   ├── activity_main_menu.xml  
│   └── ... (остальные активности)  
├── raw/  
│   ├── sleepy_beat.mp3  
│   └── vampire_soulviewy_cat.mp4  
└── values/  
    ├── colors.xml (#C7BAD7 - фон, #7711DD - акцент)  
    ├── strings.xml (все тексты)  
    └── dimens.xml (размеры кнопок)  
```

## 🔥 Инструкция по запуску

### 1. Клонирование репозитория  
```bash
git clone https://github.com/MindlessMuse666/exam-simple-app.git kval_project
cd kval_project
