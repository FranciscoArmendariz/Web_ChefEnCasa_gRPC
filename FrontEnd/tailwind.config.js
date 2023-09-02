/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        background: "#fff7ed",
        zerf: {
          DEFAULT: "#8640FF",
          contrast: "#232020",
        },
      },
    },
  },
  plugins: [],
};
