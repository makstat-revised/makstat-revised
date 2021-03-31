# MaKStat-Revised Frontend
ReactJS app with custom configuration.

### Project structure
- `/public` - stores html template page where React app is rendered. Non-react imports can be added here directly, i.e. *favicon.ico* goes here
- `/src` - stores all files that make up the React app
    - `/src/index.js` - the entrypoint of the app
    - `/src/pages` - stores website pages
    - `/src/components` - stores elements that are used by multiple pages
    - `/src/scss` - stores scss files for every element and page
    - `/src/assets` - stores static files, i.e. images, that will be served together with the frontend
- `/build` - stores the static website genreated by React for production

### Features
- Bootstrap (utility classes)
- Route configuration with 'react-router-dom'
- Fontawesome icons library

## Running the app (no Docker)

### Development
Start a live server that reloads on file changes.
```
npm install
npm run dev
```

### Production
Create a production build of the website.
```
npm install
npm run build
```
Run *serve* to serve the website from dir `build` on port `5000`.
```
npx serve -s build -l 5000
