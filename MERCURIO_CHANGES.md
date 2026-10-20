# Mercurio — Cambios respecto a BitChord

## Renombre
- App renombrada de **BitChord** a **Mercurio** (`app_name` en todos los `strings.xml`).

## Nuevo (añadido)
- **Tipografía conmutable** en Ajustes: SF Pro (BitChord) ↔ Cormorant Garamond (Hermes), con fuente Cormorant incluida en `res/font`.
- **Clima (Mercurio Weather)**:
  - `WeatherRepository` (Open-Meteo + WMO).
  - `WeatherScreen`.
  - Tarjeta de acceso en Home.
  - Notificación nativa periódica vía `AlarmManager` (`WeatherAlarmReceiver`).
- **Notas**:
  - `NotesRepository` (SharedPreferences + JSON) con colores.
  - `NotesScreen` con alta/baja.
  - Recordatorios con `NoteReminderReceiver` y botón “1h”.
- **Mascotas**:
  - `PetsScreen` con asset portado (`mercurio_pet.webp`).
- **Nube (Supabase)**:
  - `MercurioSupabase` (Auth + Postgrest) con credenciales provistas.
  - `AuthRepository` (signIn/signUp/signOut).
  - `CloudPlaylistsRepository` (list/create/addTrack).
  - `CloudScreen` (login/registro, crear playlist, listar).
- **Toasts estilo Hermes**:
  - Infraestructura `MercurioToasts` + `MercurioToastHost` (overlay en MainActivity).

## Modificado
- `BitChordTheme` acepta `fontFamily` para aplicar la fuente elegida globalmente.
- `MainActivity`: estados y overlays para Weather/Notes/Pets/Cloud; host de toasts.
- `HomeScreen`: tarjetas de acceso a Clima, Notas, Mascotas y Nube.
- `SettingsSheet`: selector de tipografía.

## Dejado sin modificar (se conserva de BitChord)
- **Full Screen Player** (diseño y comportamiento originales).
- Reproducción musical, letras, fuentes, playlists de YouTube Music/Spotify, equalizador, scrobbling, etc.
- Navegación y tabs principales.

## Notas técnicas
- Dependencias añadidas: Supabase (`bom 3.9.0-beta-1`, `auth-kt`, `postgrest-kt`) y Ktor client Android.
- Verificado: `:app:compileDevDebugKotlin` BUILD SUCCESSFUL (entorno con NDK/SDK disponible).

## Sesión 2 (logo, Supabase)
- **Logo nuevo** (`mercurio-logo.png`) aplicado en: icono adaptativo (fondo `#080808`, foreground y monocromo en `mipmap-*`), iconos legacy `ic_launcher*.webp`, las 12 variantes de icono alternativo (glass), `ic_logo.png` (app, sharedUi, desktopUi), icono de notificación (silueta blanca por densidad) y `Logo.png`.
- **Supabase**: esquema completo en `mercurio_supabase.sql` (users, playlists, playlist_tracks, rooms, messages, room_tracks, message_reactions, follows + RLS + realtime + trigger de perfil) y verificación en `mercurio_supabase_verify.sql`.
- `CloudPlaylistsRepository`: los inserts ya no envían `id` vacío (lo genera Postgres).
- `MercurioSupabase.checkConnection()` + botón "Probar conexión" en `CloudScreen`; campo de contraseña oculto.
- Pendiente: mascotas/poderes y toasts completos estilo Hermes (ver preguntas).
