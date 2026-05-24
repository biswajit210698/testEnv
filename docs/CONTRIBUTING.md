# Contributing Guide

## Code of Conduct

Be respectful, inclusive, and professional.

## How to Contribute

### 1. Fork & Clone
```bash
git clone https://github.com/your-username/testEnv.git
cd testEnv
```

### 2. Create Feature Branch
```bash
git checkout -b feature/JIRA-123-description
```

### 3. Make Changes
- Follow code style guide
- Write tests for new features
- Update documentation

### 4. Test Locally
```bash
mvn clean test
```

### 5. Commit & Push
```bash
git commit -m "[FEATURE] Add new feature description"
git push origin feature/JIRA-123-description
```

### 6. Create Pull Request
- Describe changes clearly
- Link related issues
- Request review

## Commit Message Format

```
[TYPE] Brief description (50 chars)

Detailed explanation if needed.
- Point 1
- Point 2

Fixes #123
```

### Types
- `[FEATURE]` - New feature
- `[BUGFIX]` - Bug fix
- `[TEST]` - Test addition/update
- `[DOCS]` - Documentation
- `[REFACTOR]` - Code refactoring
- `[CHORE]` - Maintenance

## Code Style

### Java Conventions
- PascalCase for classes
- camelCase for methods/variables
- UPPER_CASE for constants
- 4-space indentation
- Max line length: 120 characters

### Naming
- Descriptive names
- Avoid abbreviations
- Meaningful variable names

## Testing

All tests must:
- Pass locally
- Have meaningful assertions
- Use test data from testdata.json
- Follow Page Object Model

## Documentation

Update docs for:
- New features
- Configuration changes
- Setup modifications
- API changes

## Review Process

1. Automated checks must pass
2. At least 1 approval required
3. All comments addressed
4. Merge to master

## Questions?

Create an issue with:
- Clear title
- Detailed description
- Steps to reproduce (if bug)
- Expected vs actual behavior

Thanks for contributing! 🙏