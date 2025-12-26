const { test, expect } = require('@playwright/test');

test('게시글 목록에서 상세 페이지로 이동한다', async ({ page }) => {
  // 1. 게시글 목록 페이지 접속
  await page.goto('/posts');

  // 2. 게시글 링크 클릭 (첫 번째 글)
  await page.click('text=첫 번째 글');

  // 3. 상세 페이지로 이동했는지 확인
  await expect(page).toHaveURL(/\/posts\/\d+/);

  // 4. 상세 페이지 제목 확인
  await expect(page.locator('h1')).toContainText('게시글 상세');
});
