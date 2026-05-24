# Branch Protection & Configuration Guide

## Master Branch Configuration

This document outlines the configuration and protection rules for the `master` branch.

## Branch Protection Rules

To enable branch protection on the master branch, follow these steps in GitHub:

### 1. Access Branch Protection Settings
1. Go to: **Settings** → **Branches**
2. Click **Add rule** under "Branch protection rules"
3. Apply to branch name pattern: `master`

### 2. Required Settings

#### Basic Protection
- ✅ **Require a pull request before merging**
  - Require approvals: **1**
  - Dismiss stale pull request approvals when new commits are pushed
  - Require review from code owners

- ✅ **Require status checks to pass before merging**
  - Require branches to be up to date before merging
  - Status checks that must pass:
    - build
    - test
    - code-quality

#### Additional Security
- ✅ **Require signed commits**
- ✅ **Include administrators** - Enforce all rules above for admins too
- ✅ **Restrict who can push to matching branches**
- ✅ **Allow force pushes** - Disabled
- ✅ **Allow deletions** - Disabled

## Workflow Rules

### Only commits to master via Pull Requests
- Direct commits to master are blocked
- All changes must be reviewed and approved
- All tests must pass before merge

### Commit Message Standards
```
[TYPE] Brief description (50 chars max)

Detailed explanation (if needed)
- Point 1
- Point 2

Fixes #123
```

**Types:**
- `[FEATURE]` - New feature
- `[BUGFIX]` - Bug fix
- `[REFACTOR]` - Code refactoring
- `[DOCS]` - Documentation
- `[TEST]` - Test additions/updates
- `[CHORE]` - Build, dependencies, etc.

## Release Management

### Version Numbering (Semantic Versioning)
Format: `MAJOR.MINOR.PATCH`

- **MAJOR** - Breaking changes
- **MINOR** - New features (backward compatible)
- **PATCH** - Bug fixes

### Release Process
1. Create release branch from develop: `release/vX.Y.Z`
2. Make final adjustments and bump version
3. Create PR to master with detailed changelog
4. After merge, tag the commit: `vX.Y.Z`
5. Merge back to develop
6. Create GitHub release

## Development Workflow

```
develop branch
     ↓
feature/xxx branch
     ↓
Pull Request to develop
     ↓
Code Review + Approval
     ↓
Merge to develop
     ↓
release/vX.Y.Z branch
     ↓
Pull Request to master
     ↓
Code Review + All Checks Pass
     ↓
Merge to master (creates release)
     ↓
Tag & Create Release
```

## CI/CD Pipeline Requirements

All of these must pass before PR can be merged to master:

- ✅ Code compilation
- ✅ Unit tests (100% pass)
- ✅ Integration tests
- ✅ SonarQube code quality checks
- ✅ Dependency vulnerability scans
- ✅ Security checks

## Team Guidelines

### Who can merge?
- Repository maintainers/admins only
- At least 1 approval required from code review team

### Code Review Checklist
- [ ] Code follows project standards
- [ ] Tests are added/updated
- [ ] Documentation is updated
- [ ] No breaking changes without major version bump
- [ ] Commits are properly formatted
- [ ] All CI/CD checks pass

### Naming Conventions

**Feature branches:**
```
feature/JIRA-123-short-description
feature/add-login-functionality
```

**Bugfix branches:**
```
bugfix/JIRA-456-bug-description
bugfix/fix-login-validation
```

**Release branches:**
```
release/v1.0.0
release/v2.1.3
```

**Hotfix branches:**
```
hotfix/v1.0.1-critical-fix
hotfix/security-patch
```

## Monitoring & Enforcement

- All branch protection rules are enforced
- Status check failures block merges
- Automatic dismissal of stale reviews
- Audit logs track all merges and changes
- Required signed commits for authenticity

## Emergency Procedures

### Hotfix for Critical Issues
1. Create `hotfix/` branch from master
2. Make minimal fix with tests
3. PR directly to master (expedited review)
4. After merge, merge back to develop
5. Tag as patch version

### Temporary Bypass (Admins Only)
- Document the reason in PR
- Remove the bypass within 24 hours
- Post-incident review required

## Questions & Support

For questions about branch configuration or workflow, please:
1. Check existing documentation
2. Contact the QA/DevOps team
3. Create an issue in the repository
