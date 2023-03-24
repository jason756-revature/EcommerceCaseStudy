module.exports = {
    roots: ['./src'],
    testMatch: ['**/__tests__/**/*.js?(x)', '**/?(*.)+(spec|test).js?(x)'],
    setupFilesAfterEnv: ['@testing-library/jest-dom/extend-expect'],
  }
  
  