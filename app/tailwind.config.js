import { nextui } from '@nextui-org/react'

/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './index.html',
    './src/**/*.{js,ts,jsx,tsx}',
    './node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}',
  ],
  theme: {
    extend: {
      colors: {
        'main-gray': '#f6f6f6',
      },
    },
    fontFamily: {
      'roboto': ['Roboto', 'sans-serif']
    }
  },
  darkMode: 'class',
  plugins: [nextui()],
}
